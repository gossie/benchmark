package de.gmcs.benchmark.result;

import java.io.Writer;

public class HtmlResultWriter extends AbstractResultWriter {

    private static final String LINE_BREAK = "\n";

    public HtmlResultWriter(Writer writer) {
        super(writer);
    }

    @Override
    public void printWarmupStart() {
    }

    @Override
    public void printWarmupEnd() {
    }

	@Override
	public void printTaskGroupStart(String name) {
		String html = new StringBuilder(50)
				.append("<h2>")
				.append(name)
				.append("</h2>").append(LINE_BREAK)
				.append("<table>").append(LINE_BREAK)
                .append("<thead>").append(LINE_BREAK)
                .append("<tr>").append(LINE_BREAK)
                .append("<th>Task</th>").append(LINE_BREAK)
                .append("<th>Time (ms)</th>").append(LINE_BREAK)
                .append("</tr>").append(LINE_BREAK)
                .append("</thead>").append(LINE_BREAK)
                .append("<tbody>").append(LINE_BREAK)
				.toString();
		write(html);
	}

	@Override
	public void printTaskGroupEnd(String name) {
		String html = new StringBuilder(50)
				.append("</tbody>").append(LINE_BREAK)
				.append("</table>").append(LINE_BREAK)
				.toString();
		write(html);
	}

    @Override
    public void printTaskStart(String name) {
    }

    @Override
    public void printTaskEnd(String name, long time) {
        String html = new StringBuilder(50)
                .append("<tr>").append(LINE_BREAK)
                .append("<td>").append(name).append("</td>").append(LINE_BREAK)
                .append("<td>").append(time).append("</td>").append(LINE_BREAK)
                .append("</tr>").append(LINE_BREAK)
                .toString();
        write(html);
    }

    @Override
    public void printBenchmarkStart() {
        String html = new StringBuilder(140)
                .append("<html>").append(LINE_BREAK)
                .append("<head>").append(LINE_BREAK)
                .append("<title>Benchmark result</title>").append(LINE_BREAK)
                .append("</head>").append(LINE_BREAK)
                .append("<body>").append(LINE_BREAK)
                .toString();
        write(html);
    }

    @Override
    public void printBenchmarkEnd() {
        String html = new StringBuilder(25)
                .append("</body>").append(LINE_BREAK)
                .append("</html>").append(LINE_BREAK)
                .toString();
        write(html);
    }
}
