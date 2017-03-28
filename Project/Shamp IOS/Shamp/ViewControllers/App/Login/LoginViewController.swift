//
//  LoginViewController.swift
//  Shamp
//
//  Created by Sergio David Bravo Talero on 3/23/17.
//  Copyright © 2017 Koombea. All rights reserved.
//

import UIKit
import FacebookLogin

class LoginViewController: UIViewController {
    
    @IBOutlet weak var facebookLoginButton: UIButton!
    @IBOutlet weak var usernameTextfield: UITextField!
    @IBOutlet weak var passwordTextfield: UITextField!
    
    // MARK: - Life Cycle
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    
    // MARK: - Methods
    @IBAction func facebookButtonTapped(_ sender: Any) {
        FacebookHandler.shared.loginWithFacebookWithCompletion(viewController: self, completion: { (result) in
            if result {
                ViewControllersHandler().changeRootViewController(withName: "Home", window: self.view.window)
            }
        })
    }
    
    @IBAction func loginButtonTapped(_ sender: Any) {
    }
}
