import EmployeeOperations from './EmployeeOperations';
import './App.css';
import GetEmployees from './GetEmployees';

function App() {
  return (
    <div className="App">
      <h1>Employee Management App</h1>
      <hr/>
      <EmployeeOperations/>
      <hr/>
      <GetEmployees/>
      <hr/>
    </div>
  );
}

export default App;
