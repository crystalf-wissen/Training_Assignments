import { Routes, Route } from 'react-router-dom'
import Home from './screens/Home'
import About from './screens/About'
import Contact from './screens/Contact'
import NetBanking from './screens/NetBanking'
import Services from './screens/Services'
import Welcome from './screens/Welcome'
function App() {

  return (
    <>
      <Routes>
        <Route path="/" element={<Home/>} />
        <Route path="/about" element={<About />} />
        <Route path='/contact' element={<Contact />} />
        <Route path='/login' element={<NetBanking/>} />
        <Route path='/services' element={<Services />} />
        <Route path='/welcome' element={<Welcome />} />
        <Route path='*' element={<h1>Page Not Found</h1>} />
      </Routes>
      
    </>
  )
}

export default App
