import UIKit
import SwiftUI
import Combine
import ComposeApp


struct ContentView: View {
    @State var stateToUpdate = "11111"
    @State var showSheet = false
    @State var showDialog = false
    @State var fieldText = ""
    var height: CGFloat = 0
    var width: CGFloat = 0
    
    func printScreenDimensions(geometry: GeometryProxy) {
        print("Screen: width: \(geometry.size.width), height: \(geometry.size.height)")
    }
    

    var body: some View  {
        GeometryReader { geometry in
            Text("Screen: width: \(geometry.size.width), height: \(geometry.size.height)").zIndex(1.0)
            
            let isIgnoresSafeArea: SafeAreaRegions = if showDialog {
                .all
            } else {
                .container
            }
            
            let isNotFixWidthFrame: CGFloat = if showDialog {
                .infinity
            } else {
                //width =
                393//.infinity//414//geometry.size.width//393
            }
            
            let isNotFixHeightFrame: CGFloat = if showDialog {
                .infinity
            } else {
                852//.infinity//814//geometry.size.height//852
            }
            
            //            .frame(maxWidth: showDialog ? .infinity : 393, maxHeight: showDialog ? .infinity : 852)
            
            
            
            
            ZStack {
                VStack {
                    //                Text("Title").font(.largeTitle)
                    
                    MainScreenCompose {
                        showDialog = true
                    }
                }
                //            .frame(maxWidth: showDialog ? .infinity : 393, maxHeight: showDialog ? .infinity : 852)
                //            .frame(maxWidth: isNotFixWidthFrame, maxHeight: isNotFixHeightFrame)
                .frame(width: geometry.size.width, height: geometry.size.height + 100)
                .ignoresSafeArea(.container, edges: .all)
//                .ignoresSafeArea(isIgnoresSafeArea, edges: .all)
                .background(Color.orange)
                
                VStack {
                    CustomDialogComposeUI(showDialog: $showDialog)
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                }
                
            }
        }
    }
}

/// Old code for ContentView

//struct ContentView: View {
//    @State var stateToUpdate = "11111"
//    @State var showSheet = false
//    @State var showDialog = false
//    @State var fieldText = ""
//    var height: CGFloat = 0
//    var width: CGFloat = 0
//    
//    func printScreenDimensions(geometry: GeometryProxy) {
//        print("Screen: width: \(geometry.size.width), height: \(geometry.size.height)")
//    }
//    
//
//    var body: some View  {
//        GeometryReader { geometry in
//            //print("Screen: width: \(geometry.size.width), height: \(geometry.size.height)")
//            
//            //Text("Screen: width: \(geometry.size.width), height: \(geometry.size.height)").zIndex(1.0)
//            
//            let isIgnoresSafeArea: SafeAreaRegions = if showDialog {
//                .all
//            } else {
//                .container
//            }
//            
//            let isNotFixWidthFrame: CGFloat = if showDialog {
//                .infinity
//            } else {
//                //width =
//                .infinity//414//geometry.size.width//393
//            }
//            
//            let isNotFixHeightFrame: CGFloat = if showDialog {
//                .infinity
//            } else {
//                .infinity//814//geometry.size.height//852
//            }
//            
//            //            .frame(maxWidth: showDialog ? .infinity : 393, maxHeight: showDialog ? .infinity : 852)
//            
//            
//            
//            
//            ZStack {
//                VStack {
//                    //                Text("Title").font(.largeTitle)
//                    
//                    
//                    MainScreenCompose {
//                        showDialog = true
//                    }
//                    //                .ignoresSafeArea(.all, edges: .all)
//                    //                .ignoresSafeArea()
//                    //                .ignoresSafeArea(.container)
//                    //                .frame(width: 393, height: 852)
//                    //                .background(Color.yellow)
//                    //                .frame(maxWidth: .infinity, maxHeight: .infinity)
//                    
//                    
//                    //              Text("Main Screen").font(.largeTitle)
//                    ////                    .frame(maxWidth: .infinity, maxHeight: .infinity)
//                    //                    .background(Color.blue)
//                    //
//                    //              Text("text text text text text").font(.largeTitle)
//                    //
//                    //              TextField("Put text here", text: $fieldText)
//                    //                .font(.title2)
//                    //                .textFieldStyle(.roundedBorder)
//                    //                .padding(.horizontal, 16)
//                    //
//                    //              Button("Show Dialog") {
//                    //                withAnimation {
//                    //                  showDialog = true
//                    //                }
//                    //              }
//                    //              .buttonStyle(BorderedButtonStyle())
//                }
//                //            .frame(maxWidth: showDialog ? .infinity : 393, maxHeight: showDialog ? .infinity : 852)
//                //            .frame(maxWidth: isNotFixWidthFrame, maxHeight: isNotFixHeightFrame)
//                .frame(width: geometry.size.width, height: geometry.size.height + 100)
//                .ignoresSafeArea(.all, edges: .all)
//                //.ignoresSafeArea(isIgnoresSafeArea, edges: .all)
//                .background(Color.orange)
//                
//                VStack {
//                    CustomDialogComposeUI(showDialog: $showDialog)
//                        .frame(maxWidth: .infinity, maxHeight: .infinity)
//                }
//                
//            }
//        }
//    }
//    
////    func addMainScreenSwift() -> any View {
////        return VStack {
////          Text("Main Screen").font(.largeTitle)
////
////          Text("text text text text text").font(.largeTitle)
////
////          TextField("Put text here", text: $fieldText)
////            .font(.title2)
////            .textFieldStyle(.roundedBorder)
////            .padding(.horizontal, 16)
////
////          Button("Show Dialog") {
////            withAnimation {
////              showDialog = true
////            }
////          }
////        }
////        .frame(maxWidth: .infinity, maxHeight: .infinity)
////        .padding(8)
////        .ignoresSafeArea(isIgnoresSafeArea, edges: .all)
////    }
//    
//    
//}

#Preview {
    ContentView()
}

        
//        ZStack(alignment: .center) {
//            VStack { // Use ZStack to layer the alert over MainView
//                
//                UIKitBlockView {
//                    showSheet = true
//                    print("UIKit button clicked")
//                }
//                .frame(height: 200, alignment: .center).colorMultiply(.red)
//                
//                ComposeView {
//                    showSheet = true
//                    print("Compose UIKit button clicked")
//                }
//                
//                VStack {
//                    Text("SwiftUI Block").bold()
//                    Button("OK") {
//                        showSheet = true
//                        print("SwiftUI button clicked")
//                    }.foregroundColor(.gray).font(.title)
//
//                }
//                .frame(maxWidth: .infinity, minHeight: 300)
//                .background(.blue)
//                
//                
////                MainView(showAlert: $showAlert)
//                
////                if showAlert {
////                    CustomAlert2(
////                        title: "Alert Title",
////                        message: "This is a custom alert!",
////                        dismissButtonTitle: "OK",
////                        isButtonClicked: $showAlert,
////                        alertBlock: {
////                            print("ComposeView: Button clicked")
////                            showAlert = false
////                        }
////                    )
////                    .frame(maxWidth: .infinity, maxHeight: .infinity) // Fill the screen
////                    .background(Color.black.opacity(0.3)) // Optional: Dim the background
////                }
//            }
//
//        }
//        .sheet(isPresented: $showSheet) {
//            CustomSheetCompose(
//                title: "Alert Title",
//                message: "This is a custom alert!",
//                dismissButtonTitle: "OK",
//                isButtonClicked: $showSheet,
//                onButtonClickBlock: {
//                    print("ComposeView: Button clicked")
//                    showSheet = false
//                }
//            )
//            .frame(maxWidth: .infinity, maxHeight: .infinity).edgesIgnoringSafeArea(.all)
//            .background(Color.black) // Background of the CustomSheetCompose itself
//                
////               CustomSheetSwiftUI(
////                    title: "Alert Title",
////                    message: "This is a custom alert!",
////                    dismissButtonTitle: "OK",
////                    isButtonClicked: $showSheet,
////                    onButtonClickBlock: {
////                        showSheet = false
////                    }
////                )
////                .frame(maxWidth: .infinity, maxHeight: .infinity).edgesIgnoringSafeArea(.all)
////                .background(Color.black) // Background of the CustomSheetSwiftUI itself
//        }

//    }
//}



//func addUIForMultiplatform(showAlert: () -> Void) {
//        MainView(showAlert: $showAlert).ignoresSafeArea(.keyboard)
//
//        if showAlert { // Conditionally show the alert
//            CustomDialogSwiftUI(
//                title: "Alert Title",
//                message: "This is a custom alert!",
//                dismissButtonTitle: "OK",
//                isPresented: $showAlert,
//                showAlertPar: {
//                    print("ComposeView: Button clicked")
//                    showAlert = false
//                }
//            )
////                .frame(maxWidth: .infinity, maxHeight: .infinity) // Fill the screen
////                .background(Color.black.opacity(0.3)) // Optional: Dim the background
//        }
//}






/////////////////////
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
    @State private var bottomPadding: CGFloat = 0 // Dynamic padding for keyboard

    // ViewHeightKey Definition
    struct ViewHeightKey: PreferenceKey {
        static var defaultValue: CGFloat { 0 }
        static func reduce(value: inout Value, nextValue: () -> Value) {
            value += nextValue()
        }
    }


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
                .padding(.bottom, bottomPadding)
                .background(GeometryReader { geo in
                    Color.clear.preference(key: ViewHeightKey.self, value: geo.size.height)
                })
                .onPreferenceChange(ViewHeightKey.self) {
                    // Calculate padding dynamically based on keyboard height
                    bottomPadding = max(0, $0 - UIScreen.main.bounds.height / 3) // Adjust the divisor for desired behavior
                }
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
        .animation(.easeOut(duration: 0.25), value: bottomPadding) // Smooth animation

    }
}



//
//
//struct ContentView: View {
//    @State var stateToUpdate = "11111"
//    @State var showSheet = false
//    @State var showDialog = false
//    @State var fieldText = ""
//    
//
//    var body: some View  {
//        let isIgnoresSafeArea: SafeAreaRegions = if showDialog {
//            .all
//        } else {
//            .container
//        }
//        
//
//
//        
//        ZStack {
//            VStack {
//                MainScreenCompose {
//                    showDialog = true
//                }
////                .ignoresSafeArea(.container)
////                .frame(width: 393, height: 852)
//            }
//            .frame(maxWidth: 393, maxHeight: 852)
//            .ignoresSafeArea(isIgnoresSafeArea, edges: .all)
////            .background(Color.orange)
//
//            VStack {
//                CustomDialogComposeUI(showDialog: $showDialog)
//                    .frame(maxWidth: .infinity, maxHeight: .infinity)
//            }
//
//        }
//    }
//}
