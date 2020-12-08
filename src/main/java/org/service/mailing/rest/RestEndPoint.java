package org.service.mailing.rest;


import org.service.mailing.utils.EmailUtils;
import org.service.mailing.modal.SenderInfo;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/endpoint")
public class RestEndPoint {
@Inject
EmailUtils emailUtils;
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON )
    public Response find(SenderInfo senderInfo) {
        emailUtils.mailService(senderInfo);
        return Response.ok(senderInfo.toString())
                .build();
    }
}
