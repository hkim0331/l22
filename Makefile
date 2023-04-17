DEST=ubuntu@l.melt.kyutech.ac.jp

all: deploy

uberjar:
	asciidoctor -o resources/public/CHANGELOG.html CHANGELOG.md
	lein uberjar

deploy: uberjar
	scp target/uberjar/l22.jar ${DEST}:l22/ && \
	ssh ${DEST} 'sudo systemctl restart l22' && \
	ssh ${DEST} 'systemctl status l22'

clean:
	${RM} -r target

