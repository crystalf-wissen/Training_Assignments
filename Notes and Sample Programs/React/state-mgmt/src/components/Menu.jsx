import React from 'react'
import Button from './Button'

function Menu({count}) {
  return (
    <div>
        <h2>MENU OPTIONS with Count: {count}</h2>
        <Button count={count} />
    </div>
  )
}

export default Menu
