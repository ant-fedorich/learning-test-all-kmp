import UIKit
import SwiftUI
import Combine // Add this import statement
import ComposeApp

// CustomAlert remains the same as your provided code

struct ContentView: View {
    @State var showAlert = false
    
    let composeAlertDialogHelper = 100




    var body: some View {
        ZStack { // Use ZStack to layer the alert over MainView
            MainView(showAlert: $showAlert)
                .ignoresSafeArea(.keyboard)

            if showAlert { // Conditionally show the alert
                CustomAlert(
                    title: "Alert Title",
                    message: "This is a custom alert!",
                    dismissButtonTitle: "OK",
                    isPresented: $showAlert,
                    showAlertPar: {
                        print("ComposeView: Button clicked")
                        showAlert = true
                    }
                )
                .frame(maxWidth: .infinity, maxHeight: .infinity) // Fill the screen
                .background(Color.black.opacity(0.3)) // Optional: Dim the background
            }
        }
    }
}

struct ComposeView: UIViewControllerRepresentable {
    var showAlert: () -> Void
    func makeUIViewController(context: Context) -> UIViewController {
        ComponentsIOSKt.createComposeViewController {
            showAlert()
        }
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) { }
}


struct MainView: UIViewControllerRepresentable {
    @Binding var showAlert: Bool
//    @State var keyboardHeight: CGFloat = 0



    func makeUIViewController(context: Context) -> UIViewController {
        let uiViewController = UIViewController()
        

        // Create a SwiftUI hosting controller
        let hostingController = UIHostingController(rootView: ContentViewForHostingController(showAlert: $showAlert)) // Pass the binding

        // Add the hosting controller's view to the UIViewController
        uiViewController.addChild(hostingController)
        uiViewController.view.addSubview(hostingController.view)
        hostingController.didMove(toParent: uiViewController)

        // Constrain the hosting controller's view
        hostingController.view.translatesAutoresizingMaskIntoConstraints = false
        NSLayoutConstraint.activate([
            hostingController.view.topAnchor.constraint(equalTo:
 uiViewController.view.topAnchor),
            hostingController.view.leadingAnchor.constraint(equalTo: uiViewController.view.leadingAnchor),
            hostingController.view.trailingAnchor.constraint(equalTo: uiViewController.view.trailingAnchor),
            hostingController.view.bottomAnchor.constraint(equalTo: uiViewController.view.bottomAnchor)
        ])

        return uiViewController
    }

    class Coordinator: NSObject {
        var parent: MainView

        init(_ parent: MainView) {
            self.parent = parent
        }

        @objc func showAlert() {
            parent.showAlert = true
        }
    }

    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) { }
}


// Separate struct for the SwiftUI content you want to host
struct ContentViewForHostingController: View {
    @Binding var showAlert: Bool // Receive the binding
    @State var userInput = ""  // Holds the text field input
    @FocusState private var keyboardShown: Bool // For keyboard focus



    var body: some View {
        VStack {
            Text("Hello from SwiftUI!")
//            TextField("Your input here", text: $userInput)
//                .padding() // Add padding for better visual appearance
//                .background(
//                    RoundedRectangle(cornerRadius: 8) // Rounded border
//                        .stroke(Color.gray, lineWidth: 1) // Border color and width
//                )
//                .focused(keyboardShown) // Automatically focus on appear
//                .padding(36)
//                .task {
//                    keyboardShown = true // Focus when the view appears
//                }
            
            Button("Show Alert") {
                showAlert = true
            }
//            .alert("Important Message", isPresented: $showAlert)
//            {
//                Button("OK", role: .cancel) { }
//            } message: {
//                       Text("This is a simple alert in SwiftUI.")
//            }
            
            
            TextField("Enter text", text: $userInput)
                .frame(height: 120)
                .cornerRadius(10)
                .focused($keyboardShown)
                .task {
                    for await event in NotificationCenter.default.notifications(named: UIResponder.keyboardDidShowNotification) {
                        keyboardShown = true
                    }
                }
                .onChange(of: keyboardShown) { newValue in
                    if !newValue {
                        UIApplication.shared.sendAction(#selector(UIResponder.resignFirstResponder), to: nil, from: nil, for: nil)
                    }
                }
                .onAppear {
                    keyboardShown = true
                }
            Spacer() // Push the TextField upwards as the keyboard appears

            
            // Add more SwiftUI components here
        }
    }
}

struct CustomAlert: View {
    var title: String
    var message: String
    var dismissButtonTitle: String
    @Binding var isPresented: Bool
    var showAlertPar: () -> Void
    @State var keyboardHeight: CGFloat = 0
    @State var bottomPadding: CGFloat = 0 // Additional padding state



    var body: some View {
        GeometryReader { geometry in // Get the screen size
            
            ZStack { // Align content to the top
                Color.black.opacity(0.3).edgesIgnoringSafeArea(.all)
                VStack {
                    ComposeView(showAlert: showAlertPar).frame(maxWidth: 300, maxHeight: 100)
                    //
                    // ... (alert content)
//                    Text(title).font(.headline)
//                    Text(message)
//                        .lineLimit(nil) // Allow unlimited lines
//                        .fixedSize(horizontal: false, vertical: true) // Allow vertical expansion
//                    Text("Text text text text tte xtt ex fasd  dftt extt ext text text text text text text")
//                        .lineLimit(nil) // Allow unlimited lines
//                        .fixedSize(horizontal: false, vertical: true) // Allow vertical expansion
//                        .frame(width: 50) // Set a fixed width
//                    Button(dismissButtonTitle) {
//                        isPresented = false
//                    }
                }
                .padding()
                .background(Color.white)
                .cornerRadius(10)
                .offset(y: -bottomPadding) // Apply offset to the VStack
                .onReceive(Publishers.keyboardHeight) { keyboardHeight in
                    let screenHeight = geometry.size.height
                    let alertHeight = geometry.size.height / 2 // Estimated alert height

                    if keyboardHeight > 0 && screenHeight - keyboardHeight - alertHeight < 0 {
                        // Keyboard overlaps alert, calculate padding
                        bottomPadding = abs(screenHeight - keyboardHeight - alertHeight)
                    } else {
                        // Reset padding if enough space or keyboard hidden
                        bottomPadding = 0
                    }
                }
                .animation(.easeOut(duration: 0.25), value: bottomPadding)
            }
        }
    }
}

//struct CustomAlert: View {
//    var title: String
//    var message: String
//    var dismissButtonTitle: String
//    @Binding var isPresented: Bool
//
//    var body: some View {
//        ZStack {  // ZStack to overlay elements
//            Color.black.opacity(0.3)
//                .edgesIgnoringSafeArea(.all)
//            VStack { // Container for alert content
//                Text(title).font(.headline)
//                Text(message)
//                    .lineLimit(nil) // Allow unlimited lines
//                    .fixedSize(horizontal: false, vertical: true) // Allow vertical expansion
//                Text("Text text text text")
//                        .lineLimit(nil) // Allow unlimited lines
//                        .fixedSize(horizontal: false, vertical: true) // Allow vertical expansion
//                        .frame(width: 50) // Set a fixed width
//                Button(dismissButtonTitle) {
//                    isPresented = false
//                }
//            }
//            .padding()
//            .background(Color.white) // Customize background color
//            .cornerRadius(10)        // Customize corner radius
//        }
//    }
//}



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
