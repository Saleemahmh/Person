import io.swagger.models.*;
import io.swagger.models.parameters.Parameter;
import io.swagger.parser.SwaggerParser;

/**
 * Created by SALEEM on 9/19/2017.
 */


public class SwaggerParse {

    public static void main(String args[]) {

        Swagger swagger = new SwaggerParser().read("swagger.json");
        //String swaggerString = Json.pretty(swagger);

        System.out.println("Swagger : " + swagger.getSwagger());
        System.out.println("info: title : " + swagger.getInfo().getTitle());
        System.out.println("info: contact : email: " + swagger.getInfo().getContact().getEmail());

        System.out.println("Host : " + swagger.getHost());
        System.out.println("BasePath : " + swagger.getBasePath());
        for (String pathname : swagger.getPaths().keySet()) {
            Path path = swagger.getPaths().get(pathname);
            System.out.println();

            System.out.println("Path : " + pathname);
            System.out.println(path.getOperationMap().keySet());

            if (path.getOperations() != null) {

                for (Operation operation : path.getOperations()) {

                    System.out.println("Summary : " + operation.getSummary());
                    System.out.println("OperationId : " + operation.getOperationId());
                    if (operation.getConsumes() != null) {
                        System.out.println("Consumes : " + operation.getConsumes());
                    }
                    if (operation.getProduces() != null) {
                        System.out.println("Produces : " + operation.getProduces());
                    }
                    if (operation.getDescription() != "") {
                        System.out.println("Description : " + operation.getDescription());
                    }

                    for (Parameter parameter : operation.getParameters()) {
                        System.out.println("Parameters : Name : " + parameter.getName());
                        System.out.println("Parameters : Description : " + parameter.getDescription());

                    }

                    System.out.println();
                }
            }

        }
    }
}