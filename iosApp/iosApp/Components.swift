import Foundation
import UIKit
import SwiftUI
import Combine // Add this import statement
import ComposeApp



// SwiftUI Representable
struct UIKitBlockView: UIViewControllerRepresentable {
    let onButtonClick: () -> Void
    
    func makeUIViewController(context: Context) -> UIKitBlockViewController {
        UIKitBlockViewController {
            onButtonClick()
        }
    }

    func updateUIViewController(_ uiViewController: UIKitBlockViewController, context: Context) {  }
}
// UIKit ViewController
class UIKitBlockViewController: UIViewController {
    var onButtonClick: () -> Void
    
    required init?(coder aDecoder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }

    init(onButtonClick: @escaping () -> Void) {
         self.onButtonClick = onButtonClick
         super.init(nibName: nil, bundle: nil)
     }
        
    override func viewDidLoad() {
        super.viewDidLoad()
        
        view.backgroundColor = .red
        
        let titleLabel = UILabel()
        titleLabel.text = "UIKit Block Title"
        titleLabel.font = UIFont.preferredFont(forTextStyle: .headline)
        view.addSubview(titleLabel)
        
        let actionButton = UIButton(type: .system)
        actionButton.setTitle("Tap Me!", for: .normal)
        actionButton.tintColor = .gray
        actionButton.addTarget(self, action: #selector(buttonTapped), for: .touchUpInside)
        view.addSubview(actionButton)
        
        // Layout (Use Auto Layout, SnapKit, or programmatic constraints here)
        titleLabel.translatesAutoresizingMaskIntoConstraints = false
        actionButton.translatesAutoresizingMaskIntoConstraints = false


        NSLayoutConstraint.activate([
            titleLabel.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 20),
            titleLabel.centerXAnchor.constraint(equalTo: view.centerXAnchor),

            actionButton.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant:
 20),
            actionButton.centerXAnchor.constraint(equalTo: view.centerXAnchor)
        ])
    }

    @objc func buttonTapped() {
        onButtonClick()
        //print("Button in UIKit Block Tapped!")
    }
}

//////



// SwiftUI Representable
struct ComposeView: UIViewControllerRepresentable {
    var onButtonClick: () -> Void
    func makeUIViewController(context: Context) -> UIViewController {
        ComponentsIOSKt.createComposeViewController {
            onButtonClick()
        }
    }

    func updateUIViewController(_ uiViewController: UIViewController, context: Context) { }
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
//        GeometryReader { geometry in // Get the screen size
            
            ZStack { // Align content to the top
                Color.black.opacity(0.3).edgesIgnoringSafeArea(.all)
                VStack {
                    ComposeView(onButtonClick: showAlertPar)
                        .frame(maxWidth: .infinity, maxHeight: 1000)  // SwiftUI layout
                        .cornerRadius(10)
//                        .frame(maxWidth: .infinity) // Take up available width
                        .padding()
//                        .background(Color.red)
//                        .cornerRadius(10)

                    
//                    ComposeView(showAlertViewParam: showAlertPar)//.frame(maxWidth: 200, maxHeight: 200)
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
//                .frame(maxWidth: .infinity) // Take up available width
//                .padding(16)
//                .background(Color.red)
//                .cornerRadius(10)
//                .offset(y: -bottomPadding) // Apply offset to the VStack
//                .onReceive(Publishers.keyboardHeight) { keyboardHeight in
//                    let screenHeight = geometry.size.height
//                    let alertHeight = geometry.size.height / 2 // Estimated alert height
//
//                    if keyboardHeight > 0 && screenHeight - keyboardHeight - alertHeight < 0 {
//                        // Keyboard overlaps alert, calculate padding
//                        bottomPadding = abs(screenHeight - keyboardHeight - alertHeight)
//                    } else {
//                        // Reset padding if enough space or keyboard hidden
//                        bottomPadding = 0
//                    }
//                }
//                .animation(.easeOut(duration: 0.25), value: bottomPadding)
            }
//        }
    }
}
