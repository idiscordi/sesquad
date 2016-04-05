//
//  SignupVC.swift
//  Mobile Client
//
//  Created by Joel Garay on 3/16/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//
//import TCPIPSocket
import UIKit

class SignupVC: UIViewController {
    
    @IBOutlet weak var txtUsername: UITextField!
    @IBOutlet weak var txtPassword: UITextField!
    @IBOutlet weak var txtConfirmPassword: UITextField!
    @IBOutlet weak var txtEmail: UITextField!

    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    @IBAction func SignupTapped(sender: AnyObject) {
        
        let userUsername = txtUsername.text;
        let userPassword = txtPassword.text;
        let userConfirmPassword = txtConfirmPassword.text;
        let userEmail = txtEmail.text;
        
        uname = userUsername!;
        pword = userPassword!;
        
        //check for empty fields
        if(userUsername!.isEmpty || userPassword!.isEmpty || userConfirmPassword!.isEmpty || userEmail!.isEmpty){
            //Display alert message
            displayAlertMssg("All fields are required!");
            return;
        }
        
        //check if password matches
        if(userPassword != userConfirmPassword){
            //Display alert message
            displayAlertMssg("Passwords do not match!");
            return;
        }
        
        
        
        
        //Sending user data to server side
        
            
        //*******************************Test without server*******************************************//
        //store data locally
        NSUserDefaults.standardUserDefaults().setObject(userUsername, forKey: "userUsername");
        NSUserDefaults.standardUserDefaults().setObject(userPassword, forKey: "userPassword");
        NSUserDefaults.standardUserDefaults().setObject(userEmail, forKey: "userEmail");
        NSUserDefaults.standardUserDefaults().synchronize();
        
        //*******************************Test without server************************************//
            
        //store data
        let socket = TCPIPSocket();
        let file = NSFileHandle(fileDescriptor:socket.socketDescriptor);
        let createaccount = "createaccount:";
        let colon = ":";
        let newline = "\n";
        
        socket.connect(TCPIPSocketAddress(130, 184, 98, 90), 55000)
        file.writeData((createaccount + userUsername! + colon + userPassword! + colon + userEmail! + newline as NSString).dataUsingEncoding(NSUTF8StringEncoding)!)
        
        
        let serverResponse = file.readDataToEndOfFile()
        
        
        
        print(NSString(data: serverResponse, encoding: NSUTF8StringEncoding)!)
        
       
        
        
        
        //Display alert message with confirmation
        let someAlert = UIAlertController(title:"Alert", message:"Registration was successful!", preferredStyle:UIAlertControllerStyle.Alert);
        
        let okAction = UIAlertAction(title:"Ok", style:UIAlertActionStyle.Default){action in
            self.dismissViewControllerAnimated(true, completion:nil);
            
        }
        someAlert.addAction(okAction);
        self.presentViewController(someAlert, animated:true, completion:nil);
        
    }
   
    
    func displayAlertMssg(userMessage:String ){
        let someAlert = UIAlertController(title:"Alert", message:userMessage, preferredStyle:UIAlertControllerStyle.Alert);
        
        let okAction = UIAlertAction(title:"Ok", style:UIAlertActionStyle.Default, handler:nil);
        
        someAlert.addAction(okAction);
        
        self.presentViewController(someAlert, animated:true, completion:nil);
    }
  
    
    @IBAction func gotoLogin(sender: AnyObject) {
        self.dismissViewControllerAnimated(true, completion: nil)
    }

    

}
