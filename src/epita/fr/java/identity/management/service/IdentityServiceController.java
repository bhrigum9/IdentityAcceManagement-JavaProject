package epita.fr.java.identity.management.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.jws.WebParam;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import epita.fr.java.identity.management.authentication.Auth;
import epita.fr.java.identity.management.common.core.IdentityManagement;
import epita.fr.java.identity.management.common.core.UserDetails;
import epita.fr.java.identity.management.common.core.UserManagementVO;
import epita.fr.java.identity.management.exceptions.HandlerException;
import epita.fr.java.identity.management.handler.IDataManagementHandler;
import epita.fr.java.identity.management.handler.impl.DataManagementHandlerImpl;

/**
 * Service Class used to create REST API Calls
 * 
 * @author Bhrigu Mahajan
 *
 */
@Path("/UserService")

public class IdentityServiceController {
	private static final Logger logger = Logger.getLogger(IdentityServiceController.class);

	IDataManagementHandler userHandler = new DataManagementHandlerImpl();

	@GET
	@Path("/search/{value}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IdentityManagement> search(@PathParam("value") String value) throws HandlerException {
		List<IdentityManagement> identityManagements = new ArrayList<>();
		if (value.equals("undefined")) {
			identityManagements = userHandler.getAll();
		} else {
			try {
				identityManagements = userHandler.search(value);
			} catch (Exception e) {
				logger.error("Failed to search records" + e);

			}
		}
		return identityManagements;
	}

	@GET
	@Path("/users")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IdentityManagement> getUsers() {
		List<IdentityManagement> identityManagements = new ArrayList<>();
		try {
			identityManagements = userHandler.getAll();
		} catch (Exception e) {
			logger.error("Failed to get details" + e);

		}
		return identityManagements;
	}

	@POST
	@Path("/auth")
	@Produces(MediaType.APPLICATION_JSON)
	public IdentityManagement getUserAuth(@WebParam UserDetails userDetails) {
		Auth authUser = new Auth();
		IdentityManagement identityManagement = null;
		String id;
		try {
			id = authUser.validateUsers(userDetails);
			if (!id.isEmpty()) {
				identityManagement = getById(id);
			}
		} catch (SQLException e) {
			logger.error("Failed to login" + e);
		}

		return identityManagement;
	}

	@GET
	@Path("/id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public IdentityManagement getById(@PathParam("id") String id) {
		IdentityManagement identityManagement = new IdentityManagement();
		try {
			identityManagement = userHandler.getById(id);
		} catch (Exception e) {
			logger.error("Failed to get details" + e);
		}
		return identityManagement;
	}

	@POST
	@Path("/create")
	@Produces(MediaType.APPLICATION_JSON)
	public void saveUserDetails(@WebParam UserManagementVO userManagementVO) {
		try {
			userHandler.createUser(userManagementVO);
		} catch (Exception e) {
			logger.error("Failed to create object" + e);
		}

	}

	@DELETE
	@Path("/delete/{userid}")
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteUserDetailsById(@PathParam("userid") String uid) {
		try {
			userHandler.deleteById(uid);
		} catch (Exception e) {
			logger.error("Failed to delete" + e);
		}

	}

	@PUT
	@Path("/update")
	@Produces(MediaType.APPLICATION_JSON)
	public void updateUserDetails(@WebParam UserManagementVO userManagementVO) {
		try {
			userHandler.updateById(userManagementVO);
		} catch (Exception e) {
			logger.error("Failed to update" + e);
		}
	}
}
