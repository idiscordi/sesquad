//
//  ViewController.swift
//  Mobile Client
//
//  Created by Joel Garay on 3/16/16.
//  Copyright © 2016 SESQUAD. All rights reserved.
//

import UIKit

class ViewController: UIViewController {

    @IBOutlet weak var UsernameLabel: UILabel!
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        //Dispose of any resources that can be recreated
    }
    
    override func viewDidAppear(animated: Bool) {
        self.performSegueWithIdentifier("goto_login", sender: self)
    }
    
    @IBAction func LogoutTapped(sender: UIButton) {
        self.performSegueWithIdentifier("goto_login", sender: self)
    }
    
    @IBAction func PlayGameTapped(sender: UIButton) {
        self.performSegueWithIdentifier("goto_GameState", sender: self)
    }


}

