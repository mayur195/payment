package org.amex.resource

import org.amex.dto.LoginRequest
import org.amex.entity.User
import org.amex.service.UserService
import jakarta.ws.rs.*
import jakarta.ws.rs.core.MediaType
import jakarta.ws.rs.core.Response

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class UserResource(
    private val userService: UserService
) {

    @POST
    @Path("/register")
    fun register(user: User): Response =
        try {
            val saved = userService.register(user)
            Response.status(Response.Status.CREATED).entity(saved).build()
        } catch (e: IllegalArgumentException) {
            Response.status(Response.Status.CONFLICT).entity(mapOf("error" to e.message)).build()
        }

    @POST
    @Path("/login")
    fun login(request: LoginRequest): Response =
        if (userService.login(request.username, request.password)) {
            Response.ok(mapOf("message" to "Login successful")).build()
        } else {
            Response.status(Response.Status.UNAUTHORIZED).entity(mapOf("error" to "Invalid credentials")).build()
        }

    @GET
    fun listAll(): List<User> = userService.listAll()
}
