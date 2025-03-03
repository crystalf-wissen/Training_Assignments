<html>
    <head>
        <script>

        </script>
    </head>
    <body>
        <h1>Event Handling</h1>
        <hr>
        Enter name: <input type="text" onBlur="alert('ENTER MIN 5 LETTERS')" />
        Enter type: <input type="text" onChange="alert('YOU CHANGED THE NAME')" />
        <br></br>
        <button onclick="alert('Button Clicked!')">Click Me</button>
        <input type="button" value="CLICK" onclick="alert('GOOD MORNING')" />
        <input type="button" value="DOUBLE CLICK" onDblclick="alert('GOOD MORNING DOUBLE CLICK')" />
        <input type="button" value="ENTER" onMouseHover="alert('GOOD MORNING MOUSE HOVER')" />
        <input type="button" value="EXIT" onmouseout="alert('GOOD MORNING EXIT')" />
        <input type="button" value="CLICK AND HOLD" onmousedown="alert('GOOD MORNING CLICK AND HOLD')" />
    </body>
</html>