DEST=p.melt

all: deploy

build: hkim0331/clojure

hkim0331/clojure:
	docker build -t $@ .

target/uberjar/l22.jar: uberjar

uberjar:
	asciidoctor -o resources/public/CHANGELOG.html CHANGELOG.md
	lein uberjar

deploy: target/uberjar/l22.jar
	scp target/uberjar/l22.jar ${DEST}:l22/ && \
	ssh ${DEST} 'sudo systemctl restart l22' && \
	ssh ${DEST} 'systemctl status l22'

clean:
	${RM} -r target
