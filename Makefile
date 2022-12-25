all: deploy

build: hkim0331/clojure

hkim0331/clojure:
	docker build -t $@ .

target/uberjar/l22.jar: uberjar

uberjar:
	lein uberjar

deploy: target/uberjar/l22.jar
	scp target/uberjar/l22.jar app.melt:l22/ && \
	ssh app.melt 'sudo systemctl restart l22' && \
	ssh app.melt 'systemctl status l22'

clean:
	${RM} -r target
