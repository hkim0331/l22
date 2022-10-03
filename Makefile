all: deploy

target/default+uberjar/l22.jar: uberjar

uberjar:
	lein uberjar

deploy: target/default+uberjar/l22.jar
	scp target/default+uberjar/l22.jar app.melt:l22/ && \
	ssh app.melt 'sudo systemctl restart l22' && \
	ssh app.melt 'systemctl status l22'

clean:
	${RM} -r target

