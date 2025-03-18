import { useState } from 'react'
import './App.css'
import Menu from './components/Menu'
import { countCtx } from './state/context'

function App() {
  const [count, setCount] = useState(5)

  return (
    <>
    {/* to change value across of state across components using context */}
      <countCtx.Provider value={{count, setCount}}>
        <Menu count={count}/>
      </countCtx.Provider>
    </>
  )
}

export default App
