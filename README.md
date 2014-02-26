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
curl -v --digest -u john:123 -X GET http://localhost:8080/aerogear-integration-tests-server/rest/grocery/bacons
```

### OTP

#### Retrieve the secret
##### GET /rest/auth/otp/secret

```
curl -3 -v -b cookies.txt -c cookies.txt -H "Accept: application/json" -H "Content-type: application/json" -X GET http://localhost:8080/aerogear-integration-tests-server/rest/auth/otp/secret
```


