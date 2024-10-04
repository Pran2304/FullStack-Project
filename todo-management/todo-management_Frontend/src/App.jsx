import { useState } from 'react'
import './App.css'
import ListOfTodosComponent from './Components/ListOfTodosComponent'
import HeaderComponent from './Components/HeaderComponent'
import FooterComponent from './Components/FooterComponent'
import { BrowserRouter,Routes,Route } from 'react-router-dom'
import TodoCompo from './Components/TodoCompo'
import RegisterCompo from './Components/RegisterCompo'
import LoginComponent from './Components/LoginComponent'
import { userLoggedIn } from './Services/AuthService'

function App() {
 function AuthenticatedRoute({children}){
  const isAuth=userLoggedIn();

  if(isAuth){
    return children;
  }
  return <Navigate to="/" />
 }
  return (
    <>
    <BrowserRouter>
    <HeaderComponent/>
      <Routes> 
           {/* http:localhost:3000 */}
         <Route path='/' element={<LoginComponent/>}></Route> 
         {/* http://localhost:3000/todos */}
         <Route path='/todos' element={
         <AuthenticatedRoute>
          <ListOfTodosComponent/>
          </AuthenticatedRoute>
        }></Route>
         <Route path='/add-todo' element={<AuthenticatedRoute><TodoCompo/></AuthenticatedRoute>}></Route>
         <Route path='/update/:id' element={<AuthenticatedRoute><TodoCompo/></AuthenticatedRoute>}></Route>
         <Route path='/register' element={<RegisterCompo/>}></Route>
         <Route path='/login' element={<LoginComponent/>}></Route>
      </Routes>
    <FooterComponent/>
    </BrowserRouter>
    </>
  )
}

export default App
