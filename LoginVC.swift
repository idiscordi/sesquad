//
//  LoginVC.swift
//  Mobile Client
//
//  Created by Joel Garay on 3/16/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit


    var uname = "";
    var pword = "";
    


class LoginVC: UIViewController {
    
    @IBOutlet weak var txtUsername: UITextField!
    @IBOutlet weak var txtPassword: UITextField!


    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    @IBAction func signinTapped(sender: AnyObject) {
        let userUsername = txtUsername.text;
        let userPassword = txtPassword.text;
        
        uname = userUsername!;
        pword = userPassword!;
        
        
        let userUsernameStored = NSUserDefaults.standardUserDefaults().stringForKey("userUsername");
        let userPasswordStored = NSUserDefaults.standardUserDefaults().stringForKey("userPassword");
        
        
        
        
        //check to see if info matches
        if(userUsernameStored == userUsername && userPasswordStored == userPassword){
            //if(userPasswordStored == userPassword){
                //Login successfull
            let alert1 = UIAlertView();
            alert1.title = "Alert";
            alert1.message = "Login Successfull";
            alert1.addButtonWithTitle("Continue");
            alert1.show();
                
                NSUserDefaults.standardUserDefaults().setBool(true, forKey: "isUserLoggedIn")
                NSUserDefaults.standardUserDefaults().synchronize();
                self.dismissViewControllerAnimated(true, completion: nil);
            //}
            
        }
        
        if(userUsername!.isEmpty || userPassword!.isEmpty){
            let alert5 = UIAlertView();
            alert5.title = "Alert";
            alert5.message = "All fields must be filled!";
            alert5.addButtonWithTitle("Ok");
            alert5.show();
            
            return;
        }
        
        
       
        
        if(userUsernameStored != userUsername){
            let alert2 = UIAlertView();
            alert2.title = "Warning";
            alert2.message = "Username does not match!";
            alert2.addButtonWithTitle("Try Again");
            alert2.show();
            
            return;
        }
        
        if(userPasswordStored != userPassword){
            let alert3 = UIAlertView();
            alert3.title = "Warning";
            alert3.message = "Password does not match!";
            alert3.addButtonWithTitle("Try Again");
            alert3.show();
            
            return;
        }
        

        
        
        
        
        //read data
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let login = "login:";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((login + uname + colon + pword + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
       
        
        let serverResponse = file.readDataToEndOfFile()
        
        print(NSString(data: serverResponse, encoding: NSUTF8StringEncoding)!)

        
        
        //login successful
        NSUserDefaults.standardUserDefaults().setBool(true, forKey: "isUserLoggedIn");
        NSUserDefaults.standardUserDefaults().synchronize();
        
        self.dismissViewControllerAnimated(true, completion: nil);
      //self.performSegueWithIdentifier("goto_home", sender: self)
        
    }
    

}
