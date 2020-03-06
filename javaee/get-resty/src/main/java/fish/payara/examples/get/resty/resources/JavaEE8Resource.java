package fish.payara.examples.get.resty.resources;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author 
 */
@Path("Get-Resty")
public class JavaEE8Resource {
    
    private static final List<Person> people = new ArrayList<>();
    
    @GET
    @Path("{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("name") String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                return Response.ok(person).build();
            }
        }
        return Response.ok("No such person").build();
    }
    
    @POST
    public Response post(@QueryParam("name") String name, 
            @QueryParam("schwifty") Boolean schwifty, 
            @QueryParam("attribute") List<String> attributes) {
        people.add(new Person(name, schwifty, attributes));
        return Response.ok().build();
    }
    
    @DELETE
    @Path("{name}")
    public Response delete(@PathParam("name") String name) {
        for (Person person : people) {
            if (person.getName().equals(name)) {
                people.remove(person);
                break;
            }
        }
        return Response.ok().build();
    }
}
