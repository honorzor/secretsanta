_Docker push image_
------
##### kotlinc -script docker-push.kts ${VERSION} ${PROFILE} ${NEED_RUN}
need install kotlin.
for macos "brew install kotlin".

_Docker run container_
------
##### docker run -d -p 8081:8081 jsaymynamej/secret-santa-api:${VERSION}

_Profiles_
------
without profiles = connect to localhost db on 3306
dev = connect to dev db
prod = connect to prod db
