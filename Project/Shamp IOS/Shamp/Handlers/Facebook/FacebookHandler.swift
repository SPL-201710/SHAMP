//
//  FacebookHandler.swift
//  Shamp
//
//  Created by Sergio David Bravo Talero on 3/23/17.
//  Copyright © 2017 Koombea. All rights reserved.
//

import Foundation
import FBSDKLoginKit

class FacebookHandler {
    static let shared = FacebookHandler()
    private init() {}
    
    private let loginMananager = FBSDKLoginManager()
    
    func loginWithFacebookWithCompletion(viewController: UIViewController, completion: @escaping (_ result: Bool) -> ()) {
        loginMananager.logIn(withReadPermissions: ["email"], from: viewController, handler: { (result, error) in
            
            if let error = error {
                print("error: \(error.localizedDescription)")
                completion(false)
                AlertViewHandler().showAlerWithOkButton(fromViewController: viewController, title: "Oops", message: "Something went wrong, please try to login again.")
                return
            }
            
            completion(true)
        })
    }
    
    func isUserLoggedWithFacebook() -> Bool {
        return FBSDKAccessToken.current() != nil
    }
    
    func getFacebookCurrentAccessToken() -> String {
        return FBSDKAccessToken.current().userID
    }
}
