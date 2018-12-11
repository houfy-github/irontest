package io.irontest.resources;

import com.fasterxml.jackson.annotation.JsonView;
import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.stubbing.ServeEvent;
import com.github.tomakehurst.wiremock.stubbing.StubMapping;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Path("/mockserver") @Produces({ MediaType.APPLICATION_JSON })
public class MockServerResource {
    private WireMockServer wireMockServer;

    public MockServerResource(WireMockServer wireMockServer) {
        this.wireMockServer = wireMockServer;
    }

    @GET @Path("stubInstances")
    @JsonView(ResourceJsonViews.MockServer.class)
    public List<StubMapping> findAllStubInstances() {
        return wireMockServer.getStubMappings();
    }

    @GET @Path("stubInstances/{stubInstanceId}")
    public StubMapping findStubInstanceById(@PathParam("stubInstanceId") UUID stubInstanceId) {
        List<StubMapping> stubInstances = wireMockServer.getStubMappings();
        for (StubMapping stubInstance: stubInstances) {
            if (stubInstance.getId().equals(stubInstanceId)) {
                return stubInstance;
            }
        }
        return null;
    }

    @GET @Path("stubInstances/{stubInstanceId}/stubRequests")
    public List<ServeEvent> findRequestsForStubInstance(@PathParam("stubInstanceId") UUID stubInstanceId) {
        List<ServeEvent> result = new ArrayList<>();
        List<ServeEvent> serveEvents = wireMockServer.getAllServeEvents();
        for (ServeEvent serveEvent: serveEvents) {
            if (serveEvent.getStubMapping().getId().equals(stubInstanceId)) {
                result.add(serveEvent);
            }
        }
        return result;
    }
}
