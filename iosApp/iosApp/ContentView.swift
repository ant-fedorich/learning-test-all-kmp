import UIKit
import SwiftUI

// CustomAlert remains the same as your provided code

struct ContentView: View {
    @State private var showAlert = false


    var body: some View {
        ZStack { // Use ZStack to layer the alert over MainView
            MainView(showAlert: $showAlert)
                .ignoresSafeArea(.keyboard)

//            if showAlert { // Conditionally show the alert
//                CustomAlert(
//                    title: "Alert Title",
//                    message: "This is a custom alert!",
//                    dismissButtonTitle: "OK",
//                    isPresented: $showAlert
//                )
//                .frame(maxWidth: .infinity, maxHeight: .infinity) // Fill the screen
//                .background(Color.black.opacity(0.3)) // Optional: Dim the background
//            }
        }
    }
}

struct MainView: UIViewControllerRepresentable {
    @Binding var showAlert: Bool


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
            .alert("Important Message", isPresented: $showAlert)
            {
                Button("OK", role: .cancel) { }
            } message: {
                       Text("This is a simple alert in SwiftUI.")
            }
            
            
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

    var body: some View {
        ZStack {  // ZStack to overlay elements
            Color.black.opacity(0.3)
                .edgesIgnoringSafeArea(.all)
            VStack { // Container for alert content
                Text(title).font(.headline)
                Text(message)
                Button(dismissButtonTitle) {
                    isPresented = false
                }
            }
            .padding()
            .background(Color.white) // Customize background color
            .cornerRadius(10)        // Customize corner radius
        }
    }
}
