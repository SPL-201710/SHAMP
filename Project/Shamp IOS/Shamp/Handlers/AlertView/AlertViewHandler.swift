//
//  AlertViewHandler.swift
//  Shamp
//
//  Created by Sergio David Bravo Talero on 3/23/17.
//  Copyright © 2017 Koombea. All rights reserved.
//

import Foundation
import UIKit

class AlertViewHandler {
    
    func showAlerWithOkButton(fromViewController: UIViewController,title: String?, message: String?) {
        let alertController = UIAlertController(title: title, message: message, preferredStyle: .alert)
        let okAction = UIAlertAction(title: "Ok", style: .default, handler: nil)
        
        alertController.addAction(okAction)
        alertController.view.tintColor = UIColor.red
        alertController.present(alertController, animated: true, completion: {
            alertController.view.tintColor = UIColor.red
        })
    }
}
