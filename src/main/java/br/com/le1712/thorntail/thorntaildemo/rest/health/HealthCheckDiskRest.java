package br.com.le1712.thorntail.thorntaildemo.rest.health;

import java.io.File;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import io.swagger.annotations.Api;
import org.wildfly.swarm.health.Health;
import org.wildfly.swarm.health.HealthStatus;

@Api(tags = "/app/diskSpace")
@Path("/app/diskSpace")
@Produces(MediaType.APPLICATION_JSON)
public class HealthCheckDiskRest {

	@GET
	@Path("/diskSpace")
	@Health
	public HealthStatus checkDiskspace() {
		HealthStatus healthStatus = HealthStatus.named("checkDiskspace");
	    File path = new File(".");
	    long freeBytes = path.getFreeSpace();
	    long threshold = 1024 * 1024 * 100; // 100mb
	    return freeBytes>threshold ? healthStatus.up() : healthStatus.down().withAttribute("freebytes", freeBytes);
	}
}