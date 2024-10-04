import { useState } from "react"
import React from 'react'
import { registerAPICall } from "../Services/AuthService"
import { useNavigate } from "react-router-dom"


export default function RegisterCompo() {

    const [name,setName]=useState('')
    const [username,setUsername]=useState('')
    const [email,setEmail]=useState('')
    const [password,setPassword]=useState('')

    const navigate=useNavigate('');
  function  handleRegistrationForm(e){
    e.preventDefault();

    const register={name,username,email,password}

    console.log(register);

    registerAPICall(register).then((response)=>{
        console.log(response.data);
        navigate('/todos')
    }).catch(error=>{
        console.error(error);
  })
  }
  return (
    <div className='container'>
        <br></br>
        <br></br>
        <div className='row'></div>
        <div className='col-md-6 offset-md-3'>
            <div className='card'>
                <div className='card-header'>
                    <h2 className='text-center'>User Registration Form</h2>
                </div>
                <div className='card-body'>
                    <form>
                        <div className='row mb-3'>
                        <label className='col-md-3 control-label'>Name</label>
                        <div className='col-md-9'>
                            <input
                              type='text'
                              name='name'
                              className='foem-control'
                              placeholder='Enter name'
                              value={name}
                              onChange={(e)=>setName(e.target.value)}
                              >

                              </input>
                        </div>
                        </div>
                        <div className='row mb-3'>
                        <label className='col-md-3 control-label'>Username</label>
                        <div className='col-md-9'>
                            <input
                              type='text'
                              name='username'
                              className='foem-control'
                              placeholder='Enter Username'
                              value={username}
                              onChange={(e)=>setUsername(e.target.value)}
                              >

                              </input>
                        </div>
                        </div>
                        <div className='row mb-3'>
                        <label className='col-md-3 control-label'>Email</label>
                        <div className='col-md-9'>
                            <input
                              type='text'
                              name='email'
                              className='foem-control'
                              placeholder='Enter Email'
                              value={email}
                              onChange={(e)=>setEmail(e.target.value)}
                              >

                              </input>
                        </div>
                        </div>
                        <div className='row mb-3'>
                        <label className='col-md-3 control-label'>Password</label>
                        <div className='col-md-9'>
                            <input
                              type='text'
                              name='password'
                              className='foem-control'
                              placeholder='Enter Password'
                              value={password}
                              onChange={(e)=>setPassword(e.target.value)}
                              >

                              </input>
                        </div>
                        </div>
                        <div className="form-group mb-3">
                            <button className="btn btn-primary" onClick={(e)=>handleRegistrationForm(e)}>Submit</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

  )
}
