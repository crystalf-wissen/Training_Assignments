import './App.css';
import { Provider } from'react-redux';
import store from './reduxContainer/Store';
import BookContainer from './components/BookContainer';

function App() {
  return (
    <Provider store={store}>
      <div className="App">
      <h2>React Redux Demo</h2>
      <hr/>
      <BookContainer />
    </div>
    </Provider>

  );
}

export default App;
