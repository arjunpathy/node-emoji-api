const express = require("express");
const app = express();
const port = 3000;
const emojis = { dog: "🐶", cat: "😺", pig:"🐷", pony: "🐴", cow: "🐮"};

app.get("/", (req, res) => {
    res.send("Hello World!");
});

app.get('/emoji/:animal', (req, res) => {
    res.send(emojis[req.params['animal']])
})

app.listen(port, () => {
    console.log("listening at http://localhost: " + port);
})