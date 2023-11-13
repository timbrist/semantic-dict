package fi.coin.semanticdict.dto;

public class ResultDto {
    private String result;
    private String context;
    public ResultDto() {      }
    public String getResult() {
        return result;
    }
    public void setResult(String result) {
        this.result = result;
    }
    public String getContext() {
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }
}
