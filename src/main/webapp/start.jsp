<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Weather</title>
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
            <form method="get" action="select">
            <button type="submit">Display days table</button>
            </form>
                <details>
                    <summary>ADD</summary>
                    <form name="add" method="post" action="insert">
                        <input id="input_date" name="add_date" class="input_date" value="2020-12-31"><input id="input_max" name="max" value="20"><input id="input_min" name="min" value="7"><input id="input_event" class="input_event" name="event" value="SNOW"><br>
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
        </div>
    </div>
</div>
<footer></footer>
</body>
</html>