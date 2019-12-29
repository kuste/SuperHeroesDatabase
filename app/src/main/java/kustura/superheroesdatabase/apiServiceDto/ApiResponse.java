
package kustura.superheroesdatabase.apiServiceDto;

import java.io.Serializable;
import java.util.List;

public class ApiResponse implements Serializable {

    private String response;
    private String resultsFor;
    private List<Result> results;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getResultsFor() {
        return resultsFor;
    }

    public void setResultsFor(String resultsFor) {
        this.resultsFor = resultsFor;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

}
