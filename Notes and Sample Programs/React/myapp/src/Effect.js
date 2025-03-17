import { useEffect, useState } from "react";

function Effect() {
    const [a, setA] = useState(0)
    const [b, setB] = useState(0)

    function updateA() {
        setA(a+1)
    }
    function updateB() {
        setB(b+1)
    }
    useEffect(()=>{
        console.log("Component mounted successfully...")
    }, [b])     //avoids unnecessary re-rendering of a

    return(
        <div>
            <h2>This is from Effect Component</h2>
            <h3>Value of A: {a}</h3>
            <h3>Value of B: {b}</h3>
            <button onClick={updateA}>Update</button>
            <button onClick={updateB}>Update</button>
        </div>
    )
}

export default Effect;