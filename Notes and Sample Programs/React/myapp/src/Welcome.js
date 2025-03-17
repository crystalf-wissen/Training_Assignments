import { useRef, useState } from "react"
function Welcome() {
    const [name,setName] = useState('Guest')
    const refElement = useRef("")
    function clear(){
        setName('')
        refElement.current.focus();
        refElement.current.style.color = 'orange';
    }
    
    return(
        <div>
            <hr></hr>
            <h2>Welcome! <span>{ name }!</span></h2>
            <h3>
                <input ref={refElement}  type="text" placeholder="Enter Name" value={name} onChange={(e)=>{setName(e.target.value)}}></input>
                <button onClick={clear}>Clear</button>
            </h3>
        </div>
    )
}

export default Welcome;