

const inputImage = document.getElementById("inputImage");
const previewArea = document.getElementById("preview-image");
const placeholder = document.getElementById("placeholder");

const imageRes = document.getElementById("imageRes");
// detect upload
inputImage.addEventListener("change", () => {
    const reader = new FileReader();

    const imageFile = inputImage.files[0];
    reader.readAsDataURL(inputImage.files[0]);
    console.log(imageFile);

    var formData = new FormData();
    formData.append('image', imageFile);
    fetch('http://localhost:8888/api/image', { // URL of your server-side upload handler
        method: 'POST',
        body: formData
    })
        .then(response => response.json())
        .then(data => {
            previewArea.innerHTML = '';

            console.log('Success:', data);
            let jsonObject = JSON.parse(JSON.stringify(data));
            // Access the imageDescription property
            let imageDescription = jsonObject.imageDescription;

            //Add Description
            imageRes.innerHTML = `
            <p class="word-meaning">Description: ${ imageDescription }</p>`;

            //Draw objects in the image
            var image = document.getElementById('img');
            var canvas = document.getElementById('imageCanvas');
            var ctx = canvas.getContext('2d');

            // Set canvas size equal to image size
            canvas.width = image.width;
            canvas.height = image.height;

            // Draw the image onto the canvas
            ctx.drawImage(image, 0, 0);

            // Your JSON coordinates
            var coords = {'xmin': 103, 'ymin': 11, 'xmax': 236, 'ymax': 109};

            // Draw the rectangle
            ctx.beginPath();
            ctx.strokeStyle = 'red'; // Rectangle color
            ctx.lineWidth = 2; // Line width
            ctx.rect(coords.xmin, coords.ymin, coords.xmax - coords.xmin, coords.ymax - coords.ymin);
            ctx.stroke();

        })
        .catch((error) => {
            console.error('Error:', error);
        });
    // previewArea.innerHTML = '';
    // reader.onload = (e) => {
    //     const img = document.createElement("img");
    //     img.src = e.target.result;
    //     img.classList.add("img");
    //
    //
    //     placeholder.style.display = "none";
    //     previewArea.append(img);
    // };
});
