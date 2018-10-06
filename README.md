## Deprecation Notice
This repository has been deprecated and is not being maintained. If you have any questions, please get in touch with the collaborators.

# AeroGear Integration Tests Server

## API

### Pipe

#### GET /rest/team/developers

Retrieve list of team developers

```
curl -v \
     -H "Accept: application/json" \
     -H "Content-type: application/json" \
     -X GET http://localhost:8080/aerogear-integration-tests-server/rest/team/developers
```


#### POST /rest/upload

Handles a multipart request by simply echoing backs to the client the values posted. For file objects, it echoes the overall size of the file.

**NOTE** files are not persisted on disk.

```
curl -v \
	 -F "somekey=somevalue" \
	 -F "file=@/path/to/a/filename" \
	 http://localhost:8080/aerogear-integration-tests-server/rest/upload
```

will return a JSON object of a form:

```
{"filename":"<file length>","somekey":"somevalue"}
```

### Push

#### POST /rest/push/send

Send message to devices

PS: For the endpoint to work you must setup a [UnifiedPush server](https://github.com/aerogear/aerogear-unifiedpush-server/) and adjust the Constants.java file to point to it

```
curl -v \
     -X POST http://localhost:8080/aerogear-integration-tests-server/rest/push/send \
     -d  'This is my message'
```

### Authenticaton

#### Database
##### POST /rest/auth/login

```
curl -3 -v -b cookies.txt -c cookies.txt -H "Accept: application/json" -H "Content-type: application/json" -d '{"loginName":"john","password":"123"}' -X POST http://localhost:8080/aerogear-integration-tests-server/rest/auth/login
```

#### Basic

##### GET /rest/grocery/beers
```
curl -v -X GET -u john:123 http://localhost:8080/aerogear-integration-tests-server/rest/grocery/beers

```

#### Digest

##### GET /rest/grocery/bacons

```
curl -b --cookie -v --user agnes:123 --digest -X GET http://localhost:8080/aerogear-integration-tests-server/rest/grocery/bacons
```

### OTP

#### Retrieve the secret
##### GET /rest/auth/otp/secret

```
curl -3 -v -b cookies.txt -c cookies.txt -H "Accept: application/json" -H "Content-type: application/json" -X GET http://localhost:8080/aerogear-integration-tests-server/rest/auth/otp/secret
```

### OAuth2 with Keycloak 

#### Pre-requisites

* use [Keycloak Appliance Distribution](http://docs.jboss.org/keycloak/docs/1.0-alpha-3/userguide/html/server-installation.html) comes with a preconfigured Keycloak server (based on Wildfly). Tested with keycloak-appliance-dist-all-1.0-alpha-4
* import realm [configuration file](configuration/testrealm.json)
* in ```src/main/webapp/WEB-INF/web.xml```, uncomment the lines after "Enable Keycloak Oauth2 configuration". Those lines have been commented to be able to deploy integration-tests-server to wildfly/jBoss7 without Keycloak configuration not to mess up other features testing.

#### GET /rest/portal/products
This rest point is configured to work as a public client OAuth2 endpoint.
Use [ProductInventory](https://github.com/aerogear/aerogear-ios-cookbook/tree/master/ProductInventory) iOS app to test the OAuth2 access.

