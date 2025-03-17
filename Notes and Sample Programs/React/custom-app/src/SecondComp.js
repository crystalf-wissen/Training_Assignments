import useCounter from "./useCounter";

function SecondComp() {
    const [cnt, incr, decr ] = useCounter()

    return (
        <div>
            <h2>Second Component:</h2>
            <h3>{cnt}</h3>
            <br></br>
            <button onClick={incr}>Increment</button>
            <button onClick={decr}>Decrement</button>
        </div>
    )
}

export default SecondComp;