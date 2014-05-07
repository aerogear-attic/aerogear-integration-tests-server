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

import org.jboss.aerogear.integration.model.Developer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/team")
public class TeamService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/developers")
    public List<Developer> team() {
        List<Developer> team = new ArrayList<Developer>();

        Developer abstractj = new Developer("Bruno Oliveira", "abstractj", "https://pbs.twimg.com/profile_images/1864227872/avatar.png");
        team.add(abstractj);

        Developer balunasj = new Developer("Jay Balunas", "tech4j", "https://pbs.twimg.com/profile_images/1822424039/bio_photo_130x160.jpg");
        team.add(balunasj);

        Developer corinnekrych = new Developer("Corinne Krych", "corinnekrych", "https://pbs.twimg.com/profile_images/1804433301/corinne_1_.png");
        team.add(corinnekrych);

        Developer cvasilak = new Developer("Christos Vasilakis", "cvasilak", "https://pbs.twimg.com/profile_images/3303764456/a6476bc06717ad4c4d8eba1a9afa7c37.jpeg");
        team.add(cvasilak);

        Developer dbevenius = new Developer("Daniel Bevenius", "dbevenius", "https://pbs.twimg.com/profile_images/1129481589/Photo_on_2010-07-29_at_10.11__2.jpg");
        team.add(dbevenius);

        Developer edewit = new Developer("Erik Jan de Wit", "edewit", "https://pbs.twimg.com/profile_images/446669128028262400/zpXaZjcw.jpeg");
        team.add(edewit);

        Developer lholmquist = new Developer("Lucas Holmquist", "sienaluke", "https://pbs.twimg.com/profile_images/3026702973/a29460575807a55269b34cb96acbe0f6.jpeg");
        team.add(lholmquist);

        Developer matzew = new Developer("Matthias Wessendorf", "mwessendorf", "https://pbs.twimg.com/profile_images/446674276129579008/nbPgKi8m.jpeg");
        team.add(matzew);

        Developer passos = new Developer("Daniel Passos", "passos", "https://pbs.twimg.com/profile_images/452656410778927105/2qdd9NhO_400x400.jpeg");
        team.add(passos);

        Developer qmx = new Developer("Douglas Campos", "qmx", "https://pbs.twimg.com/profile_images/378800000802700429/05ee67897e2cd187c0d5fe782b389a23.jpeg");
        team.add(qmx);

        Developer sblanc = new Developer("Sébastien Blanc", "sebi2706", "https://pbs.twimg.com/profile_images/436582986654314497/BWinFmZ0.jpeg");
        team.add(sblanc);

        Developer summersp = new Developer("Summers Pittman", "summerspittman", "https://pbs.twimg.com/profile_images/2936447891/ebe9da7e1797936b921fa056404d52a3.jpeg");
        team.add(summersp);

        return team;
    }

}
