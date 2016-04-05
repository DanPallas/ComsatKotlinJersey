package comsatkotlinjersey

import co.paralleluniverse.fibers.jersey.ServletContainer
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.ServletContextHandler
import org.eclipse.jetty.servlet.ServletHolder
import org.glassfish.jersey.jackson.JacksonFeature
import org.glassfish.jersey.server.ResourceConfig
import javax.ws.rs.ext.ContextResolver
import javax.ws.rs.ext.Provider

/**
 * Created by Dan Pallas on 4/3/16.
 */

fun init() {
    val server = Server(8080)
    val handler = ServletContextHandler()
    handler.contextPath = ""
    val servletHolder = ServletHolder(ServletContainer(AppConfig()))
    handler.addServlet(servletHolder, "/*")
    server.handler = handler
    server.start()
    server.join()
}

class AppConfig : ResourceConfig {
    constructor(): super() {
        register(HelloWorldController())
        register(KotlinJacksonProvider())
        register(JacksonFeature::class.java)
    }
}

@Provider
class KotlinJacksonProvider: ContextResolver<ObjectMapper> {
    override fun getContext(type: Class<*>?): ObjectMapper? {
        return jacksonObjectMapper()
    }
}

fun main(args: Array<String>) {
    init()
    /*separate function from init for dependency injection. Currently, init doesn't take any arguments now, but it
    could take a guice injector or manual dependency injection arguments. */
}