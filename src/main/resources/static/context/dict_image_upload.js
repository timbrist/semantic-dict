

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
            console.log('Success:', data);
            let jsonObject = JSON.parse(JSON.stringify(data));
            // Access the imageDescription property
            let imageDescription = jsonObject.imageDescription;

            imageRes.innerHTML = `
            <p class="word-meaning">Description: ${ imageDescription }</p>`;
        })
        .catch((error) => {
            console.error('Error:', error);
        });
    previewArea.innerHTML = '';
    reader.onload = (e) => {
        const img = document.createElement("img");
        img.src = e.target.result;
        img.classList.add("img");


        placeholder.style.display = "none";
        previewArea.append(img);
    };
});

//
// document.getElementById('uploadForm').addEventListener('submitImage', function(event) {
//     event.preventDefault(); // Prevent the default form submission
//
//     var formData = new FormData();
//     var imageFile = document.getElementById('imageInput').files[0];
//     formData.append('image', imageFile);
//
//     fetch('/image', { // URL of your server-side upload handler
//         method: 'POST',
//         body: formData
//     })
//         .then(response => response.json())
//         .then(data => {
//             console.log('Success:', data);
//         })
//         .catch((error) => {
//             console.error('Error:', error);
//         });
// });