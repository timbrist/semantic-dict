
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.http.HTTPRepository;

public class CottageRepository{
    private Repository repo;
    private String rdf4jServer = "http://localhost:8080/rdf4j-server/";
    private String PREFIX = "PREFIX ctg: <http://localhost:8080/rdf4j-servlet-0.1/CottageBooking/onto#> \n";
    private String repositoryID = "task6";
    private RepositoryConnection connection;
    private String defaultQuery = "SELECT ?s ?p ?o\n" + //
                    "WHERE{\n" + //
                    "   ?s ?p ?o. \n" + //
                    "} ";
    public CottageRepository(){
        this.repo = new HTTPRepository(this.rdf4jServer, this.repositoryID);
        repo.init();
        this.connection = repo.getConnection();
    }
    public CottageRepository(String rdf4jServer,String repositoryID){
        this.repo = new HTTPRepository(rdf4jServer,repositoryID);
        repo.init();
        this.connection = repo.getConnection();
    }
    public String Search(String query){
        StringBuilder resultString = new StringBuilder();
        TupleQuery tupleQuery = this.connection.prepareTupleQuery(QueryLanguage.SPARQL, query);
        TupleQueryResult result = tupleQuery.evaluate();
        try {
            while (result.hasNext()) {
            BindingSet bindingSet = result.next();
            bindingSet.getBindingNames().forEach(bindingName -> {
                resultString.append(bindingName)
                            .append(": ")
                            .append(bindingSet.getValue(bindingName))
                            .append("\t");
                });
            resultString.append("\n");
            }
        } finally { result.close(); }
        // Convert the StringBuilder to a String
        String resultAsText = resultString.toString();
        return resultAsText;
    }

    public String ListAll(){
        return this.Search(this.defaultQuery);
    }
    public String ListAllCottage(){
        String query = PREFIX + //
                "SELECT ?cottage\n" + //
                "WHERE {\n" + //
                "  ?cottage a ctg:Cottage .\n" + //
                "}";
        String result = this.Search(query);
        return result;
    }

    public String ListBookedCottage(){
        String query = PREFIX + //
                "SELECT ?cottage\n" + //
                "WHERE {\n" + //
                "  ?cottage a ctg:Cottage ;\n" + //
                "           ctg:isBooked true .\n" + //
                "}";
        String result = this.Search(query);
        return result;
    }

    public String ShowCottageInfo(long cottageId){
        
        String query1 = PREFIX + //
                "SELECT ?cottage ?name ?address ?distanceToCity ?distanceToLake ?hasBeds ?hasPlaces\n" + //
                "WHERE {\n" + //
                "  ?cottage a ctg:Cottage ;\n" + //
                "           ctg:hasId "+ cottageId + ";  \n" + //
                "           ctg:hasName ?name ;\n" + //
                "           ctg:hasAddress ?address ;\n" + //
                "           ctg:distanceToCity ?distanceToCity ;\n" + //
                "           ctg:distanceToLake ?distanceToLake ;\n" + //
                "           ctg:hasBeds ?hasBeds ;\n" + //
                "           ctg:hasPlaces ?hasPlaces.\n" + //
                "}";
        String query2 = PREFIX +//
                "SELECT ?isbooked \n" + //
                "WHERE {\n" + //
                "  ?cottage a ctg:Cottage;\n" + //
                "           ctg:hasId "+ cottageId + ";  \n" + //
                "           ctg:isBooked ?isbooked.\n" + //
                "}";
        String result = this.Search(query1);
        String result2 = this.Search(query2);
        String condiction = "isbooked: \"true\"";
        String reservedInfo = "" ;
        if(result2.contains(condiction)){
            //System.out.println(result2);
            String query3 = PREFIX + //
                    "SELECT ?start ?end\n" + //
                    "WHERE {\n" + //
                    "  ?cottage a ctg:BookedCottage ;\n" + //
                    "    \t   ctg:hasCottageId 2 ;\n" + //
                    "           ctg:bookStartAt ?start;\n" + //
                    "    \t   ctg:bookEndAt ?end;\n" + //
                    "}";
            reservedInfo = this.Search(query3);
        }
        else{
            reservedInfo = "no researvation";
        }
        String finalResult = result + reservedInfo;
        return finalResult;
    }


}
