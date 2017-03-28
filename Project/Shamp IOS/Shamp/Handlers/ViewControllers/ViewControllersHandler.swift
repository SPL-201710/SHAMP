//
//  ViewControllersHandler.swift
//  Shamp
//
//  Created by Sergio David Bravo Talero on 3/23/17.
//  Copyright © 2017 Koombea. All rights reserved.
//

import Foundation
import UIKit

class ViewControllersHandler {

    func changeRootViewController(withName name: String, window: UIWindow?) {
        let storyboard = UIStoryboard(name: name, bundle: nil)
        let controller = storyboard.instantiateInitialViewController()
        
        window?.rootViewController = controller
    }
}
