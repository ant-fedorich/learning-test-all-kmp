//
//  SmsManager.swift
//  iosApp
//
//  Created by eltonio on 11.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import UIKit

@objc public class SmsHandler: NSObject {
   @objc public static func sendSms(number: String, message: String) {
        let sms = "sms:\(number)&body=\(message)"
        if let strURL =
            sms.addingPercentEncoding(withAllowedCharacters: .urlQueryAllowed),
           let url = URL(string: strURL) {
            UIApplication.shared.open(url, options: [:], completionHandler: nil)
        } else {
            print("Invalis SMS URL")
        }
    }
}

@objc public class HelloWorld : NSObject {
    @objc public class func helloWorld() -> String {
        return "HeLLo WorLd!"
    }
}


