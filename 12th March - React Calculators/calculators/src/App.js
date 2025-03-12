import './App.css';
import LoanCalculator from './components/LoanCalculator';
import DepositCalculator from './components/DepositCalculator';

function App() {
  return (
    <div className="App">
      <LoanCalculator />
      <hr></hr>
      <DepositCalculator />
    </div>
  );
}

export default App;
