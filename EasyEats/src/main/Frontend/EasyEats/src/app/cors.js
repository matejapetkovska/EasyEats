const express = require('express');
const cors = require('cors');

const app = express();


app.use(cors());

app.get('/api/data', (req, res) => {
  res.json({message: 'Hello from the backend!'});
});

const port = 8081;
app.listen(port, () => {
  console.log(`Backend server is running on port ${port}`);
});
