java -javaagent:./agent-instrumentation/target/agent-fat.jar -cp ".;agent-test/target/classes" -jar ./time-complexity-analyser/target/time-complexity-analyser-fat.jar tech.dario.dissertation.agenttest.Main