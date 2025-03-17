import JsxDemo from './JsxDemo';
import Greet from './Greet';
import Counter from './Counter';
import Welcome from './Welcome';
import Effect from './Effect';
function App() {
  const a = 10;
  const b = 20

  return (
    <>
      <h1>THIS IS MY FIRST APPLICATION</h1>
      <hr></hr>
      <JsxDemo />
      <h2>The sum of {a} and {b} is: {a +b }</h2>
      {
        // Conditional rendering
        a % 2 === 0 ? <h3>The number is even</h3> : <h3>The number is odd</h3>  // Inline conditional rendering
      }
      <hr></hr>
      <Greet name="Crystal" day="Wednesday"/>
      <hr></hr>
      <Counter/>
      <Welcome />
      <hr></hr>
      <Effect />
    </>
  );
}

export default App;