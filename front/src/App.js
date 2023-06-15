import React, { useEffect, useState } from 'react';
import axios from 'axios';
import Authentication from './components/Authentication';
import './App.css';

function App() {
  const [message, setMessage] = useState('');

  useEffect(() => {
    axios.get('http://localhost:9000/test')
      .then(response => setMessage(response.data))
      .catch(error => console.error('Error:', error));
  }, []);

  return (
    <Authentication />
    // <div>
    //   {message}
    // </div>
  );
}
export default App;
