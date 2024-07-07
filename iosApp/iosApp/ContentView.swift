import UIKit
import SwiftUI
import ComposeApp

struct ComposeView: UIViewControllerRepresentable {
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

struct ContentView: View {
    var body: some View {
        ComposeView()
                .ignoresSafeArea(.keyboard) // Compose has own keyboard handler
    }
}

//
////added
//class ComposeUIViewController: UIViewController {
//    override func viewDidLoad() {
//        super.viewDidLoad()
//
//        let composeView = ComposeView()
//        composeView.setContent {
//            CommonButton(text: "Click Me", onClick = {
//                // Handle button click
//            })
//        }
//
//        view = composeView
//    }
//}
//
//
////Added
//@UIApplicationMain
//class AppDelegate: UIResponder, UIApplicationDelegate {
//
//    var window: UIWindow?
//
//    func application(
//        _ application: UIApplication,
//        didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
//    ) -> Bool {
//        window = UIWindow(frame: UIScreen.main.bounds)
//
//        let composeViewController = ComposeUIViewController()
//
//        window?.rootViewController = composeViewController
//        window?.makeKeyAndVisible()
//
//        return true
//    }
//}



