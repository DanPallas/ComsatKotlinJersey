package comsatkotlinjersey

import co.paralleluniverse.fibers.Suspendable
import co.paralleluniverse.strands.Strand
import comsatkotlinjersey.model.json.HelloWorldResponse
import javax.ws.rs.GET
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response

/**
 * Created by Dan Pallas on 4/3/16.
 */
@Path("hw")
class HelloWorldController {
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    fun get(): Response {
        return Response.ok("hello world").build()
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Suspendable
    fun getWithName(@PathParam("name") name: String): HelloWorldResponse{
        Strand.sleep(3000) // fiber blocking
        return HelloWorldResponse("Hello, $name!")
    }
}