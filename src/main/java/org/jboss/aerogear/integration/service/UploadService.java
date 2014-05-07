/**
 * JBoss, Home of Professional Open Source
 * Copyright Red Hat, Inc., and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.aerogear.integration.service;

import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A service that handles a 'multipart/form-data' request.
 * Currently it simply echoes backs to the client the values posted. For 'file' parts, it echoes the
 * overall size of the file.
 * <p>
 *     Note: file objects are not persisted on-disk for obvious reasons.
 * </p>
 */
@Path("/upload")
public class UploadService {

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, String> upload(final MultipartFormDataInput input) throws IOException {
        final Map<String, String> response = new HashMap<String, String>();

        // extract form elements
        final Map<String, List<InputPart>> formElements = input.getFormDataMap();

        // iterate over the elements
        for (String name : formElements.keySet()) {
            final List<InputPart> parts = formElements.get(name);

            for (InputPart part : parts) {
                final String filename = getFileName(part.getHeaders());

                // for file types
                if (filename != null) {
                    // calculate the file size and add it to the response
                    final byte[] content = part.getBody(byte[].class, null);
                    response.put(filename, String.format("%d bytes", content.length, "bytes"));

                } else { // for all other types
                    // simply add the value
                    response.put(name, part.getBodyAsString());
                }
            }
        }

        return response;
    }

    // util method to extract 'filename' from the headers
    private String getFileName(final MultivaluedMap<String, String> headers) {
        final String[] attrs = headers.getFirst("Content-Disposition").split(";");

        for (String attr : attrs) {
            if ((attr.trim().startsWith("filename"))) {
                // handle filename='somefile'
                final String[] arr = attr.split("=");

                // trim and replace quotes
                return arr[1].trim().replaceAll("\"", "");

            }
        }
        return null;
    }
}
