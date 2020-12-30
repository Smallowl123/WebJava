<%@ page import="model.Day" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>table</title>
    <link rel="stylesheet" href="weather.css">
</head>
<body>
<header>
    <div class="container">
        <div class="header">
            Weather
        </div>
    </div>
</header>
<div class ="container">
    <div class="body">
        <div class="day_block">
            <table class ="table">
                <thead>
                <tr>
                    <th>Date</th>
                    <th>MAX, °C</th>
                    <th>MIN, °C</th>
                    <th>Event</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Day> dayList = (List) request.getAttribute("dayList");
                    for(Day day: dayList){
                %>
                        <tr>
                            <td><%= day.getDate()%></td>
                            <td class="t_row"><%= day.getMaxTemperatureC()%></td>
                            <td class="t_row"><%= day.getMinTemperatureC()%></td>
                            <td><%= day.getW_Event()%></td>
                        </tr>
                <%}%>
                </tbody>
            </table>
            <details>
                <summary>ADD</summary>
                <form name="add" method="post" action="insert">
                    <input id="input_date" name="add_date" class="input_date" value="2020-12-31"><input id="input_max" name="max" value="20"><input id="input_min" name="min" value="7"><input id="input_event" name="event" class="input_event" value="SNOW"><br>
                    <button id="addButton" type="submit">Add</button>
                </form>
            </details>
            <details>
                <summary>DEL</summary>
                <form id="del" method="post" action="delete">
                    <input name="del_date" id="del_input_date" class="input_date" value="2020-12-31">
                    <button id="delButton" type="submit">Del</button>
                </form>

            </details>
            <%
                String message = " ";
                if (request.getAttribute("message") != null){
                    message = (String) request.getAttribute("message");
                }
            %>
            <%=message%>
        </div>
    </div>
</div>
<footer></footer>
</body>
</html>