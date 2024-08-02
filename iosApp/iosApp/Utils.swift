//
//  Utils.swift
//  iosApp
//
//  Created by eltonio on 03.08.2024.
//  Copyright © 2024 orgName. All rights reserved.
//

import UIKit
import SwiftUI
import Combine // Add this import statement
import ComposeApp


/////////////////
// Keyboard Height Publisher
extension Publishers {
    static var keyboardHeight: AnyPublisher<CGFloat, Never> {
        let willShow = NotificationCenter.default.publisher(for: UIApplication.keyboardWillShowNotification)
            .map { $0.keyboardHeight }
        let willHide = NotificationCenter.default.publisher(for: UIApplication.keyboardWillHideNotification)
            .map { _ in CGFloat(0) }
        return MergeMany(willShow, willHide)
            .eraseToAnyPublisher()

    }
}

extension Notification {
    var keyboardHeight: CGFloat {
        return (userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? CGRect)?.height
 ?? 0
    }
}
